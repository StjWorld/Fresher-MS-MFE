import 'bulma/css/bulma.css';
import Routes from './Route';



function App() {


    return (
        <div className='container mx-auto grid grid-cols-6 gamp-4 mt-4'>
            <div className='col-span-5'>
                <Routes/>
            </div>
        </div>
    );
}

export default App;