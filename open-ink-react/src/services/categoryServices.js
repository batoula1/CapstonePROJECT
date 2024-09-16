import { axiosRequest } from "../utils/constants";

//get all categories
export const getAllCategories = async (query) => {
    return await axiosRequest.get("/api/category/all")
        .then(resp => resp.data);
}