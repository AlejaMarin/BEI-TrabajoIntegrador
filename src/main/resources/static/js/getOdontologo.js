window.addEventListener('load', function () {

    const formulario = document.querySelector('#find');
    const respuesta = document.querySelector('#respuesta');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const idOdontologo = document.querySelector('#id').value;

        const url = '/odontologos/' + idOdontologo;
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
                <p>${data.matricula}</p>
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
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";
    }
})