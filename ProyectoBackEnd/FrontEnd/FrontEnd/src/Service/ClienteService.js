import axios from "axios";

const URL = "http://localhost:8080/clientes";

export const listarClientes = () => axios.get(URL);

export const guardarCliente = (cliente) => axios.post(`${URL}/save`, cliente);

export const eliminarCliente = (id) => axios.delete(`${URL}/eliminar/${id}`);

export const buscarCliente = (id) => axios.get(`${URL}/${id}`);

export const actualizarCliente = (id, cliente) => axios.put(`${URL}/actualizar/${id}`, cliente);