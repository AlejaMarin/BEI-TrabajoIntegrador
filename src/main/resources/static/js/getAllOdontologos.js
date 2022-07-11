window.addEventListener('load', function () {

    const respuesta = document.querySelector('#respuesta');

    const url = '/odontologos/';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            data.map(odontologo => {
                return respuesta.innerHTML += `
                <div>
                <p>${odontologo.nombre}</p>
                <p>${odontologo.apellido}</p>
                <p>${odontologo.matricula}</p>
                </div>
                `
            })
        })
        .catch(error => {
            console.log(error);
        })
});