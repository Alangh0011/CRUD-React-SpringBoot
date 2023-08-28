import './App.css';
import ListClientesComponent from './Componentes/ListClientesComponent';
import HeaderComponent from './Componentes/HeaderComponent';
import FooterComponent from './Componentes/FooterComponent';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AddClienteComponent from './Componentes/AddClienteComponent';

function App() {
  return (
    <div>
      <BrowserRouter>{/* */}
        <HeaderComponent/>
          <div className='container'>
            <Routes>
              <Route exact path='/' element={<ListClientesComponent/>}></Route>
              <Route path='/clientes' element={<ListClientesComponent/>}></Route>
              <Route path='/add-cliente' element={<AddClienteComponent/>}></Route>
              <Route path='/edit-cliente/:id' element={<AddClienteComponent/>}></Route>
            </Routes>
          </div>
        <FooterComponent/>
      </BrowserRouter>
    </div>
  );
}

export default App;
