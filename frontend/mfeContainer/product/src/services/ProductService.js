import axios from "axios";


// This is Service class
class ProductService {
    getProducts(){
        return axios.get(`http://localhost:8181/api`);
    }
   /* getProductById(id) {
        return axios.get(`http://localhost:8181/api/${id}`);
    }
    delete(id){
        return axios.delete(`localhost:8181/api/${id}`);
    }
    update(id,product){
        return axios.put(`localhost:8181/api/${id}`, product);
    }
    create(product){
        return axios.post(`localhost:8181/api`, product);
    }*/
}

export default new ProductService();