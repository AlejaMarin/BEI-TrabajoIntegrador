window.addEventListener('load', function () {

    const respuesta = document.querySelector('#respuesta');

    const url = '/pacientes/';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            data.map(paciente => {
                return respuesta.innerHTML += `
                <div>
                <p>${paciente.nombre}</p>
                <p>${paciente.apellido}</p>
                <p>${paciente.dni}</p>
                <p>${paciente.fechaAlta}</p>
                <p>Domicilio:</p>
                <p>${paciente.domicilio.calle}</p>
                <p>${paciente.domicilio.numero}</p>
                <p>${paciente.domicilio.localidad}</p>
                <p>${paciente.domicilio.provincia}</p>
                </div>
                `
            })
        })
        .catch(error => {
            console.log(error);
        })
});