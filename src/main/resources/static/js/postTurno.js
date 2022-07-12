window.addEventListener('load', function () {

    const formulario = document.querySelector('#create');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const datosNuevoOdontologo = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };

        const url = '/odontologos/guardar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(datosNuevoOdontologo)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                alert("Odontologo agregado correctamente");
                console.log(data);
                resetForm();
            })
            .catch(error => {
                alert("No se pudo agregar el odontológo");
                resetForm();
            })
    });

    function resetForm() {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";
    }
})