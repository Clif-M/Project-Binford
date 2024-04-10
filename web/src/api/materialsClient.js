import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";
/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class MaterialsClient extends BindingClass {
    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'editMaterial', 'addMaterial', 'deleteMaterial', 'getMaterials', 'search'];
        this.bindClassMethods(methodsToBind, this);
        this.authenticator = new Authenticator();;
        this.props = props;
        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }
    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }
    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();
            if (!isLoggedIn) {
                return undefined;
            }
            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    async login() {
        this.authenticator.login();
    }
    async logout() {
        this.authenticator.logout();
    }
    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }
        return await this.authenticator.getUserToken();
    }
    //---------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------
    async getSingleMaterial(orgId, materialId, errorCallback) {
        try {
            const response = await this.axiosClient.get(`organizations/${orgId}/materials/${materialId}`);
            return response.data.material;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getMaterials(orgId, errorCallback) {
        try {
            const response = await this.axiosClient.get(`organizations/${orgId}/materials`);
            return response.data.projectList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    async editMaterial(orgId, materialId, cost, inventoryCount, isExpendable, name, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can edit materials");
            const response = await this.axiosClient.put(`organizations/${orgId}/materials/${materialId}`, {
                            orgId: orgId,
                            materialId: materialId,
                            cost: cost,
                            inventoryCount: inventoryCount,
                            isExpendable: isExpendable,
                            name: name
                        }, {
                            headers: {
                                Authorization: `Bearer ${token}`
                            }
                        });
            return response.data.materialList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
        async addMaterial(orgId, cost, inventoryCount, isExpendable, name, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can add materials");
                const response = await this.axiosClient.post(`material`, {
                                orgId: orgId,
                                cost: cost,
                                inventoryCount: inventoryCount,
                                isExpendable: isExpendable,
                                name: name
                            }, {
                                headers: {
                                    Authorization: `Bearer ${token}`
                                }
                            });
                return response.data.materialList;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }
    async deleteMaterial(orgId, materialId, cost, inventoryCount, isExpendable, name, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can edit materials");
            const response = await this.axiosClient.delete(`materials/${materialId}/organizations/${orgId}`, {
                            orgId: orgId,
                            materialId: materialId,
                        }, {
                            headers: {
                                Authorization: `Bearer ${token}`
                            }
                        });
            return response.data.materialList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    async search(criteria, errorCallback) {
            try {
                const queryParams = new URLSearchParams({ q: criteria })
                const queryString = queryParams.toString();
                const response = await this.axiosClient.get(`organizations/${queryString}/materials`);
                return response.data.playlists;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }
    handleError(error, errorCallback) {
        console.error(error);
        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }
        if (errorCallback) {
            errorCallback(error);
        }
    }
}