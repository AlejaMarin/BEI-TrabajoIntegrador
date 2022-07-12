window.addEventListener('load', function () {

    const formulario = document.querySelector('#find');
    const respuesta = document.querySelector('#respuesta');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const idPaciente = document.querySelector('#id').value;

        const url = '/pacientes/' + idPaciente;
        const settings = {
            method: 'GET'
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                respuesta.innerHTML = `
                <div>
                <p>${data.nombre}</p>
                <p>${data.apellido}</p>
                <p>${data.dni}</p>
                <p>${data.fechaAlta}</p>
                <p>Domicilio:</p>
                <p>${data.domicilio.calle}</p>
                <p>${data.domicilio.numero}</p>
                <p>${data.domicilio.localidad}</p>
                <p>${data.domicilio.provincia}</p>
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