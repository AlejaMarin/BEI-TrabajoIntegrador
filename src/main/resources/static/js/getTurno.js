window.addEventListener('load', function () {

    const formulario = document.querySelector('#find');
    const respuesta = document.querySelector('#respuesta');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const idTurno = document.querySelector('#id').value;

        const url = '/turnos/' + idTurno;
        const settings = {
            method: 'GET'
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                respuesta.innerHTML = `
                <div>
                <p>Paciente:<p>
                <p>${data.paciente.nombre}</p>
                <p>${data.paciente.apellido}</p>
                <p>${data.paciente.dni}</p>
                <p>${data.paciente.fechaAlta}</p>
                <p>Domicilio:</p>
                <p>${data.paciente.domicilio.calle}</p>
                <p>${data.paciente.domicilio.numero}</p>
                <p>${data.paciente.domicilio.localidad}</p>
                <p>${data.paciente.domicilio.provincia}</p>
                <p>Odontológo:<p>
                <p>${data.odontologo.nombre}</p>
                <p>${data.odontologo.apellido}</p>
                <p>${data.odontologo.matricula}</p>
                <p>Fecha y Hora: ${data.fechaHora}</p>
                </div>
                `
            })
            .catch(error => {
                alert("El ID ingresado no existe");
                console.log(error);
                resetForm();
            })
    });

    function resetForm() {
        document.querySelector('#id').value = "";
    }
})