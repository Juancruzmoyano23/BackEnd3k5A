import { useEffect, useState } from "react";

import {listarClientes, guardarCliente, eliminarCliente, buscarCliente, actualizarCliente} from "../Service/ClienteService";

function Clientes() {

    const [clientes, setClientes] = useState([]);
    const [tipoDoc, setTipoDoc] = useState("");
    const [nroDoc, setNroDoc] = useState("");
    const [apellido, setApellido] = useState("");
    const [nombre, setNombre] = useState("");
    const [calle, setCalle] = useState("");
    const [nroCalle, setNroCalle] = useState("");
    const [ciudadId, setCiudadId] = useState("");
    const [idBusqueda, setIdBusqueda] = useState("");
    const [clienteActual, setClienteActual] = useState(null);

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

    const limpiarFormulario = () => {
        setTipoDoc("");
        setNroDoc("");
        setApellido("");
        setNombre("");
        setCalle("");
        setNroCalle("");
        setCiudadId("");
        setClienteActual(null);
        setIdBusqueda("");
    };

    const buscar = () => {
        if (!idBusqueda) {
            alert("Ingresa un ID para buscar");
            return;
        }

        console.log("🔍 Buscando cliente con id:", idBusqueda);

        buscarCliente(parseInt(idBusqueda))
            .then(res => {
                console.log("✅ Cliente encontrado:", res.data);
                const cliente = res.data;
                setClienteActual(cliente);
                setTipoDoc(cliente.tipo_doc || "");
                setNroDoc(cliente.nro_doc || "");
                setApellido(cliente.apellido || "");
                setNombre(cliente.nombre || "");
                setCalle(cliente.calle || "");
                setNroCalle(cliente.nro_calle || "");
                setCiudadId(cliente.ciudad?.id || "");
            })
            .catch(error => {
                console.error("❌ Error al buscar cliente:", error.message);
                alert("Cliente no encontrado");
            });
    };

    const editar = (cliente) => {
        console.log("✏️ Editando cliente:", cliente);
        setClienteActual(cliente);
        setTipoDoc(cliente.tipo_doc || "");
        setNroDoc(cliente.nro_doc || "");
        setApellido(cliente.apellido || "");
        setNombre(cliente.nombre || "");
        setCalle(cliente.calle || "");
        setNroCalle(cliente.nro_calle || "");
        setCiudadId(cliente.ciudad?.id || "");
        window.scrollTo(0, 0);
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

        if (clienteActual) {
            // Actualizar cliente existente
            actualizarCliente(clienteActual.id, cliente)
                .then(() => {
                    console.log("✅ Cliente actualizado exitosamente");
                    cargarClientes();
                    limpiarFormulario();
                    alert("Cliente actualizado correctamente");
                })
                .catch(error => {
                    console.error("❌ Error al actualizar cliente:", error.message);
                    alert("Error: " + error.message);
                });
        } else {
            // Crear nuevo cliente
            guardarCliente(cliente)
                .then(() => {
                    console.log("✅ Cliente guardado exitosamente");
                    cargarClientes();
                    limpiarFormulario();
                    alert("Cliente guardado correctamente");
                })
                .catch(error => {
                    console.error("❌ Error al guardar cliente:", error.message);
                    alert("Error: " + error.message);
                });
        }
    };

    const eliminar = (id) => {
        console.log("🗑️ Eliminando cliente con id:", id);

        eliminarCliente(id)
            .then(() => {
                console.log("✅ Cliente eliminado exitosamente");
                cargarClientes();
                limpiarFormulario();
                alert("Cliente eliminado correctamente");
            })
            .catch(error => {
                console.error("❌ Error al eliminar cliente:", error.message);
                alert("Error: " + error.message);
            });
    };

    return (

        <div className="container mt-4">

            <h2>Clientes {clienteActual && `(Editando ID: ${clienteActual.id})`}</h2>

            <div className="row mb-3">
                <div className="col-md-6">
                    <input
                        className="form-control"
                        placeholder="Buscar cliente por ID"
                        type="number"
                        value={idBusqueda}
                        onChange={(e) => setIdBusqueda(e.target.value)}
                    />
                </div>
                <div className="col-md-3">
                    <button
                        className="btn btn-info w-100"
                        onClick={buscar}
                    >
                        Buscar
                    </button>
                </div>
                <div className="col-md-3">
                    <button
                        className="btn btn-secondary w-100"
                        onClick={limpiarFormulario}
                    >
                        Limpiar
                    </button>
                </div>
            </div>

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

            <div className="row mb-3">
                <div className="col-md-6">
                    <button
                        className={`btn w-100 ${clienteActual ? 'btn-warning' : 'btn-primary'}`}
                        onClick={guardar}
                    >
                        {clienteActual ? 'Actualizar Cliente' : 'Guardar Cliente'}
                    </button>
                </div>
                {clienteActual && (
                    <div className="col-md-6">
                        <button
                            className="btn btn-secondary w-100"
                            onClick={limpiarFormulario}
                        >
                            Cancelar Edición
                        </button>
                    </div>
                )}
            </div>

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
                                    className="btn btn-warning btn-sm me-2"
                                    onClick={() => editar(cliente)}
                                >
                                    Editar
                                </button>

                                <button
                                    className="btn btn-danger btn-sm"
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
