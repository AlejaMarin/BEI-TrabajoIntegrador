window.addEventListener('load', function () {

    const formulario = document.querySelector('#update');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const datosTurno = {
            id: document.querySelector('#id').value,
            paciente: {
                id: document.querySelector('#idPaciente').value
            },
            odontologo: {
                id: document.querySelector('#idOdontologo').value
            },
            fechaHora: document.querySelector('#fechaHora').value
        };

        const url = '/turnos/modificar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(datosTurno)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                alert("Turno modificado correctamente");
                console.log(data);
                resetForm();
            })
            .catch(error => {
                alert("No se pudo modificar el turno");
                resetForm();
            })
    });

    function resetForm() {
        document.querySelector('#id').value = "";
        document.querySelector('#idPaciente').value = "";
        document.querySelector('#idOdontologo').value = "";
        document.querySelector('#fechaHora').value = "";
    }
})