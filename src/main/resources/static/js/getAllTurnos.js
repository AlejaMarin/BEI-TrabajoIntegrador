window.addEventListener('load', function () {

    const respuesta = document.querySelector('#respuesta');

    const url = '/turnos/';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            data.map(turno => {
                return respuesta.innerHTML += `
                <div>
                <p>Paciente:<p>
                <p>${turno.paciente.nombre}</p>
                <p>${turno.paciente.apellido}</p>
                <p>${turno.paciente.dni}</p>
                <p>${turno.paciente.fechaAlta}</p>
                <p>Domicilio:</p>
                <p>${turno.paciente.domicilio.calle}</p>
                <p>${turno.paciente.domicilio.numero}</p>
                <p>${turno.paciente.domicilio.localidad}</p>
                <p>${turno.paciente.domicilio.provincia}</p>
                <p>Odontológo:<p>
                <p>${turno.odontologo.nombre}</p>
                <p>${turno.odontologo.apellido}</p>
                <p>${turno.odontologo.matricula}</p>
                <p>Fecha y Hora: ${turno.fechaHora}</p>
                </div>
                `
            })
        })
        .catch(error => {
            console.log(error);
        })
});