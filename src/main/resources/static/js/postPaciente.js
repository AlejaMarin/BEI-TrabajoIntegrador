window.addEventListener('load', function () {

    const formulario = document.querySelector('#create');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const datosNuevoPaciente = {
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

        const url = '/pacientes/guardar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(datosNuevoPaciente)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                alert("Paciente agregado correctamente");
                console.log(data);
                resetForm();
            })
            .catch(error => {
                alert("No se pudo agregar el paciente");
                resetForm();
            })
    });

    function resetForm() {
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