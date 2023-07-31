import {Route, Switch} from "react-router-dom";
import ProductPage from "./pages/ProductPage";
import { BrowserRouter } from "react-router-dom/cjs/react-router-dom.min";

const Routes = () => {

    return (
        <BrowserRouter>
        <Switch>
        <Route path="/" component={ProductPage} />
        </Switch>
        </BrowserRouter>
    );
};

export default Routes;