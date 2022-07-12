window.addEventListener('load', function () {

    const formulario = document.querySelector('#delete');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const idTurno = document.querySelector('#id').value;

        const url = '/turnos/eliminar/' + idTurno;
        const settings = {
            method: 'DELETE'
        };

        fetch(url, settings)
            .then()
            .then(() => {
                alert("Turno eliminado correctamente");
                resetForm();
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