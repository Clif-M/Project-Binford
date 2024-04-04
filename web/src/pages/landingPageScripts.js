
import OrganizationClient from '../api/organizationClient';
import UserRoleClient from '../api/userRoleClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/*
The code below this comment is equivalent to...
const EMPTY_DATASTORE_STATE = {
    'search-criteria': '',
    'search-results': [],
};

...but uses the "KEY" constants instead of "magic strings".
The "KEY" constants will be reused a few times below.
*/

const COGNITO_NAME_KEY = 'cognito-name';
const COGNITO_EMAIL_KEY = 'cognito-name-results';
const USER_ROLES_KEY = 'user-roles'
const EMPTY_DATASTORE_STATE = {
    [COGNITO_NAME_KEY]: '',
    [COGNITO_EMAIL_KEY]: '',
    [USER_ROLES_KEY]: [],
};


/**
 * Logic needed for the view playlist page of the website.
 */
class LandingPageScripts extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'test', 'startupActivities'], this);

        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        //this.dataStore.addChangeListener(this.startupActivities);
        console.log("landingPageScripts constructor");
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        // Wire up the form's 'submit' event and the button's 'click' event to the search method.
        document.getElementById('test-btn').addEventListener('click', this.test);

        this.header.addHeaderToPage();

        this.organizationClient = new OrganizationClient();
        this.userRoleClient = new UserRoleClient();
        document.getElementById('title').innerText = "Welcome to [Project Binford]. Please log-in at the top right to continue.";
        this.startupActivities();
    }

    async startupActivities() {
        //If user is logged in when app starts, this method will initialize page elements
        if (await this.organizationClient.verifyLogin()) {
            const{email, name} = await this.organizationClient.getIdentity().then(result => result);
            this.dataStore.set([COGNITO_EMAIL_KEY], email);
            this.dataStore.set([COGNITO_NAME_KEY], name);
            document.getElementById('title').innerText = `Hello ${name}, please choose a role to continue.`;
            
            document.getElementById('userRoles').hidden = false;
        }
    }

    async getRolesForUser(cognitoEmail) {
        this.dataStore.set([USER_ROLES_KEY], await this.userRoleClient.get)
    }


    async test() {
        const[cognitoEmail, cognitoName] = await this.organizationClient.getIdentity().then(result => result);
        alert(cognitoEmail);
    }

    /**
     * Uses the client to perform the search, 
     * then updates the datastore with the criteria and results.
     * @param evt The "event" object representing the user-initiated event that triggered this method.
     */
    // async search(evt) {
    //     // Prevent submitting the from from reloading the page.
    //     evt.preventDefault();

    //     const searchCriteria = document.getElementById('search-criteria').value;
    //     const previousSearchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);

    //     // If the user didn't change the search criteria, do nothing
    //     if (previousSearchCriteria === searchCriteria) {
    //         return;
    //     }

    //     if (searchCriteria) {
    //         const results = await this.client.search(searchCriteria);

    //         this.dataStore.setState({
    //             [SEARCH_CRITERIA_KEY]: searchCriteria,
    //             [SEARCH_RESULTS_KEY]: results,
    //         });
    //     } else {
    //         this.dataStore.setState(EMPTY_DATASTORE_STATE);
    //     }
    // }

    /**
     * Pulls search results from the datastore and displays them on the html page.
     */
    // displaySearchResults() {
    //     const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
    //     const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);

    //     const searchResultsContainer = document.getElementById('search-results-container');
    //     const searchCriteriaDisplay = document.getElementById('search-criteria-display');
    //     const searchResultsDisplay = document.getElementById('search-results-display');

    //     if (searchCriteria === '') {
    //         searchResultsContainer.classList.add('hidden');
    //         searchCriteriaDisplay.innerHTML = '';
    //         searchResultsDisplay.innerHTML = '';
    //     } else {
    //         searchResultsContainer.classList.remove('hidden');
    //         searchCriteriaDisplay.innerHTML = `"${searchCriteria}"`;
    //         searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
    //     }
    // }

    // /**
    //  * Create appropriate HTML for displaying searchResults on the page.
    //  * @param searchResults An array of playlists objects to be displayed on the page.
    //  * @returns A string of HTML suitable for being dropped on the page.
    //  */
    // getHTMLForSearchResults(searchResults) {
    //     if (searchResults.length === 0) {
    //         return '<h4>No results found</h4>';
    //     }

    //     let html = '<table><tr><th>Name</th><th>Song Count</th><th>Tags</th></tr>';
    //     for (const res of searchResults) {
    //         html += `
    //         <tr>
    //             <td>
    //                 <a href="playlist.html?id=${res.id}">${res.name}</a>
    //             </td>
    //             <td>${res.songCount}</td>
    //             <td>${res.tags?.join(', ')}</td>
    //         </tr>`;
    //     }
    //     html += '</table>';

    //     return html;
    // }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const landingPageScripts = new LandingPageScripts();
    landingPageScripts.mount();
};

window.addEventListener('DOMContentLoaded', main);
