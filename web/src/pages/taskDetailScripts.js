
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
const EMPTY_DATASTORE_STATE = {
    [COGNITO_NAME_KEY]: '',
    [COGNITO_EMAIL_KEY]: '',
    [ORG_ID_KEY]: '',
    [PROJECT_ID_KEY]: '',
    [TASK_ID_KEY]: '',
    [MATERIAL_LIST_KEY]: [],
    [TASK_OBJECT_KEY]: '',
    [ASSIGNEE_LIST_KEY]: [],
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
            this.dataStore.set([TASK_OBJECT_KEY], await this.taskClient.getSingleTask(orgId, taskId));
            await this.populateAssigneeList();
            document.getElementById('name').value = task.name;

            // await this.populateTable();
             var preloads = document.getElementsByClassName('preload')
             for (var i= 0; i < preloads.length; i++) {
                 preloads[i].hidden=false
             }
             document.getElementById('loading').hidden=true;
            // document.getElementById('completed').addEventListener('change', await this.populateTable)
            // document.getElementById('start').addEventListener('change', await this.populateTable)
            // document.getElementById('end').addEventListener('change', await this.populateTable)
        } else {
            window.location.href = "index.html"
        }
    }

    async populateAssigneeList() {
        const assigneeList = this.userRoleClient.
    }

    async populateTable() {
//        var table = document.getElementById("task-table");
//        var oldTableBody = table.getElementsByTagName('tbody')[0];
//        var newTableBody = document.createElement('tbody');
//        var taskList = this.dataStore.get(TASKLIST_KEY);
//        for(const task of taskList) {
//            const date = new Date(task.startTime *1000)
//            if (
//                (task.completed==false || document.getElementById('completed').checked==true) &&
//                (date >= new Date(document.getElementById('start').value)) &&
//                (date <= new Date(document.getElementById('end').value))
//            ) {
//
//                var row = newTableBody.insertRow(0);
//                var cell1 = row.insertCell(0);
//                var cell2 = row.insertCell(1);
//                var cell3 = row.insertCell(2);
//                var cell4 = row.insertCell(3);
//                cell1.innerHTML = task.name;
//                cell2.innerHTML = task.hoursToComplete;
//                cell3.innerHTML = date.toDateString();
//                cell4.innerHTML = task.completed;
//                var createClickHandler = function(row) {
//                    return function() {
//                        for (var i = 0; i < table.rows.length; i++){
//                            table.rows[i].removeAttribute('class');
//                        }
//                        row.setAttribute('class','selectedRow')
//                        document.getElementById('view-btn').setAttribute('href', 'workerTaskListDetail?orgId=' + new URLSearchParams(window.location.search).get('orgId') + "&taskId=" + task.taskId);
//                    };
//                };
//                row.onclick = createClickHandler(row);
//            }
//        }
//        oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
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
