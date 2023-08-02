import axios from "axios";



class ProductService {
    getProducts(){
        return axios.get(`http://localhost:8181/api`);
    }
   /* getProductById(id) {
        return axios.get(`http://localhost:8181/products/${id}`);
    }
    delete(id){
        return axios.delete(`localhost:8181/products/${id}`);
    }
    update(id,product){
        return axios.put(`localhost:8181/products/${id}`, product);
    }
    create(product){
        return axios.post(`localhost:8181/products`, product);
    }*/
}

export default new ProductService();