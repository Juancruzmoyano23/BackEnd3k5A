import { useEffect, useState } from "react";

import {listarClientes, guardarCliente, eliminarCliente} from "../Service/ClienteService";

function Clientes() {

    const [clientes, setClientes] = useState([]);
    const [tipoDoc, setTipoDoc] = useState("");
    const [nroDoc, setNroDoc] = useState("");
    const [apellido, setApellido] = useState("");
    const [nombre, setNombre] = useState("");
    const [calle, setCalle] = useState("");
    const [nroCalle, setNroCalle] = useState("");
    const [ciudadId, setCiudadId] = useState("");

    useEffect(() => {cargarClientes();}, []);

    const cargarClientes = () => {
        listarClientes()
            .then(res => {
                console.log("✅ Clientes cargados:", res.data);
                setClientes(res.data);
            })
            .catch(error => {
                console.error("❌ Error al cargar clientes:", error.message);
                alert("Error: " + error.message);
            });
    };

    const guardar = () => {
        const cliente = {
            tipo_doc: tipoDoc,
            nro_doc: parseInt(nroDoc),
            apellido,
            nombre,
            calle,
            nro_calle: parseInt(nroCalle),
            ciudad: ciudadId ? { id: parseInt(ciudadId) } : null
        };

        console.log("📤 Enviando cliente:", cliente);

        guardarCliente(cliente)
            .then(() => {
                console.log("✅ Cliente guardado exitosamente");
                cargarClientes();

                setTipoDoc("");
                setNroDoc("");
                setApellido("");
                setNombre("");
                setCalle("");
                setNroCalle("");
                setCiudadId("");
                alert("Cliente guardado correctamente");
            })
            .catch(error => {
                console.error("❌ Error al guardar cliente:", error.message);
                alert("Error: " + error.message);
            });
    };

    const eliminar = (id) => {
        console.log("🗑️ Eliminando cliente con id:", id);

        eliminarCliente(id)
            .then(() => {
                console.log("✅ Cliente eliminado exitosamente");
                cargarClientes();
                alert("Cliente eliminado correctamente");
            })
            .catch(error => {
                console.error("❌ Error al eliminar cliente:", error.message);
                alert("Error: " + error.message);
            });
    };

    return (

        <div className="container mt-4">

            <h2>Clientes</h2>

            <div className="row">
                <div className="col-md-6">
                    <input
                        className="form-control mb-2"
                        placeholder="Tipo de Documento"
                        value={tipoDoc}
                        onChange={(e) => setTipoDoc(e.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        className="form-control mb-2"
                        placeholder="Número de Documento"
                        type="number"
                        value={nroDoc}
                        onChange={(e) => setNroDoc(e.target.value)}
                    />
                </div>
            </div>

            <div className="row">
                <div className="col-md-6">
                    <input
                        className="form-control mb-2"
                        placeholder="Apellido"
                        value={apellido}
                        onChange={(e) => setApellido(e.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        className="form-control mb-2"
                        placeholder="Nombre"
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                    />
                </div>
            </div>

            <div className="row">
                <div className="col-md-6">
                    <input
                        className="form-control mb-2"
                        placeholder="Calle"
                        value={calle}
                        onChange={(e) => setCalle(e.target.value)}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        className="form-control mb-2"
                        placeholder="Número de Calle"
                        type="number"
                        value={nroCalle}
                        onChange={(e) => setNroCalle(e.target.value)}
                    />
                </div>
            </div>

            <div className="row mb-3">
                <div className="col-md-6">
                    <input
                        className="form-control"
                        placeholder="ID de Ciudad"
                        type="number"
                        value={ciudadId}
                        onChange={(e) => setCiudadId(e.target.value)}
                    />
                </div>
            </div>

            <button
                className="btn btn-primary mb-4"
                onClick={guardar}
            >
                Guardar
            </button>

            <table className="table table-bordered">

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tipo Doc</th>
                        <th>Nro Doc</th>
                        <th>Apellido</th>
                        <th>Nombre</th>
                        <th>Calle</th>
                        <th>Nro Calle</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>

                    {clientes.map(cliente => (

                        <tr key={cliente.id}>

                            <td>{cliente.id}</td>
                            <td>{cliente.tipo_doc}</td>
                            <td>{cliente.nro_doc}</td>
                            <td>{cliente.apellido}</td>
                            <td>{cliente.nombre}</td>
                            <td>{cliente.calle}</td>
                            <td>{cliente.nro_calle}</td>

                            <td>

                                <button
                                    className="btn btn-danger"
                                    onClick={() => eliminar(cliente.id)}
                                >
                                    Eliminar
                                </button>

                            </td>

                        </tr>

                    ))}

                </tbody>

            </table>

        </div>

    );
}

export default Clientes;