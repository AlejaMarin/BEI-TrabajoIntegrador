window.addEventListener('load', function () {

    const formulario = document.querySelector('#create');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const datosNuevoTurno = {
            paciente: {
                id: document.querySelector('#idPaciente').value
            },
            odontologo: {
                id: document.querySelector('#idOdontologo').value
            },
            fechaHora: document.querySelector('#fechaHora').value
        };

        const url = '/turnos/guardar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(datosNuevoTurno)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                alert("Turno agregado correctamente");
                console.log(data);
                resetForm();
            })
            .catch(error => {
                alert("No se pudo agregar el turno");
                resetForm();
            })
    });

    function resetForm() {
        document.querySelector('#idPaciente').value = "";
        document.querySelector('#idOdontologo').value = "";
        document.querySelector('#fechaHora').value = "";
    }
})