window.addEventListener('load', function () {

    const formulario = document.querySelector('#update');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const datosPaciente = {
            id: document.querySelector('#id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaAlta: document.querySelector('#fechaIngreso').value,
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value
            }
        };

        const url = '/pacientes/modificar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(datosPaciente)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                alert("Paciente modificado correctamente");
                console.log(data);
                resetForm();
            })
            .catch(error => {
                alert("No se pudo modificar el paciente");
                resetForm();
            })
    });

    function resetForm() {
        document.querySelector('#id').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#fechaIngreso').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
    }
})