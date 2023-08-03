import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { LifeCycles, registerApplication, start } from "single-spa";

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

registerApplication({
  name: "product",
  app: (): Promise<LifeCycles> => (window as any).System.import("http://localhost:8081/spa-product-product.js"),
  activeWhen: "/product"
})


start();
