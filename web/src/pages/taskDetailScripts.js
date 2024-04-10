
import TaskClient from '../api/taskClient';
import UserRoleClient from '../api/userRoleClient';
import MaterialsClient from '../api/materialsClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

const COGNITO_NAME_KEY = 'cognito-name';
const COGNITO_EMAIL_KEY = 'cognito-name-results';
const ORG_ID_KEY = 'organization-id-key';
const TASK_ID_KEY = 'task-id-key';
const PROJECT_ID_KEY = 'project-id-key;'
const MATERIAL_LIST_KEY = 'material-list-key';
const TASK_OBJECT_KEY = 'task-object-key';
const ASSIGNEE_LIST_KEY = 'assignee-list-key';
const USER_OBJECT_KEY = 'user-object-key';
const EMPTY_DATASTORE_STATE = {
    [COGNITO_NAME_KEY]: '',
    [COGNITO_EMAIL_KEY]: '',
    [ORG_ID_KEY]: '',
    [PROJECT_ID_KEY]: '',
    [TASK_ID_KEY]: '',
    [MATERIAL_LIST_KEY]: [],
    [TASK_OBJECT_KEY]: '',
    [ASSIGNEE_LIST_KEY]: [],
    [USER_OBJECT_KEY]: '',
};



class TaskDetailScripts extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'startupActivities', 'populateTable'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
    }


    mount() {
        this.header.addHeaderToPage();
        this.taskClient = new TaskClient();
        this.userRoleClient = new UserRoleClient();
        this.materialsClient = new MaterialsClient();
        this.startupActivities();
    }

    async startupActivities() {

        if (await this.userRoleClient.verifyLogin()) {
            const orgId = new URLSearchParams(window.location.search).get('orgId');
            this.dataStore.set([ORG_ID_KEY], orgId)
            const taskId = new URLSearchParams(window.location.search).get('taskId');
            this.dataStore.set([TASK_ID_KEY], taskId)
            this.dataStore.set([PROJECT_ID_KEY], new URLSearchParams(window.location.search).get('projectId'))
            const{email, name} = await this.userRoleClient.getIdentity().then(result => result);
            this.dataStore.set([COGNITO_EMAIL_KEY], email);
            this.dataStore.set([COGNITO_NAME_KEY], name);
            const task = await this.taskClient.getSingleTask(orgId, taskId);
            this.dataStore.set([TASK_OBJECT_KEY], task);
            await this.populateAssigneeList();
            document.getElementById('name').value = task.name;
            if(task.completed == true) {
                document.getElementById('completed').value = "Yes"
            } else {
                document.getElementById('completed').value = "No"
            }
            document.getElementById('hours').value = task.hoursToComplete
            if(task.startTime != null) { document.getElementById('startTime').value = (new Date(task.startTime *1000)).toDateString() }
            if(task.endTime != null) { document.getElementById('endTime').value = (new Date(task.endTime *1000)).toDateString() }
            document.getElementById('taskNotes').value = task.taskNotes

            this.dataStore.set([USER_OBJECT_KEY], await this.userRoleClient.getUserRole(email, orgId))

            if (this.dataStore.get(USER_OBJECT_KEY).jobRole == "Manager") {
                document.getElementById('name').removeAttribute('disabled')
                document.getElementById('users').removeAttribute('disabled')
                document.getElementById('hours').removeAttribute('disabled')
            }

            await this.populateTable();
             var preloads = document.getElementsByClassName('preload')
             for (var i= 0; i < preloads.length; i++) {
                 preloads[i].hidden=false
             }
             document.getElementById('loading').hidden=true;
             document.getElementById('save-btn').hidden=false;
             document.getElementById('cancel-btn').hidden=false;

             if(task.completed) {
                document.getElementById('uncomplete-btn').hidden=false;
             } else {
                document.getElementById('complete-btn').hidden=false;
             }
            document.getElementById('save-btn').addEventListener('click', await this.populateTable)
            // document.getElementById('start').addEventListener('change', await this.populateTable)
            // document.getElementById('end').addEventListener('change', await this.populateTable)
        } else {
            window.location.href = "index.html"
        }
    }

    async populateAssigneeList() {
        var userList = await this.userRoleClient.getUsersForOrg(this.dataStore.get([ORG_ID_KEY]));
        userList = userList.filter((user) => user.roleStatus == 'Active')
        const select = document.getElementById('users');
        for (const user of userList) {
            var opt = document.createElement('option');
            opt.innerText = user.displayName.concat(" ~ ", user.jobRole);
            select.appendChild(opt);
        }
        const assignee = this.dataStore.get([TASK_OBJECT_KEY]).assignee;
        if (assignee !== null) {

            for (let i=0; i < userList.length; i++) {
                if (userList[i].userEmail === assignee) {
                    select.selectedIndex = i + 1;
                }
            }
        }
    }

    async populateTable() {
        var table = document.getElementById("material-table");
        var oldTableBody = table.getElementsByTagName('tbody')[0];
        var newTableBody = document.createElement('tbody');
        var materialList = this.dataStore.get(TASK_OBJECT_KEY).materialsList;
        for(const material of materialList) {
                const fullMaterial = await this.materialsClient.getSingleMaterial(material.orgId, material.materialId)
                if (fullMaterial != null) {
                    var row = newTableBody.insertRow(0);
                    var cell1 = row.insertCell(0);
                    var cell2 = row.insertCell(1);
                    cell1.innerHTML = fullMaterial.name;
                    cell2.innerHTML = material.inventoryCount;
                    var createClickHandler = function(row) {
                    return function() {
                        for (var i = 0; i < table.rows.length; i++){
                            table.rows[i].removeAttribute('class');
                        }
                        row.setAttribute('class','selectedRow')
                    }};
                row.onclick = createClickHandler(row);
            }
        }
        oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
    }

}


/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const taskDetailScripts = new TaskDetailScripts();
    taskDetailScripts.mount();
};

window.addEventListener('DOMContentLoaded', main);
