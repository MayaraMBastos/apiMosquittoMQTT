//// Transforma a mensagem em um objeto JSON antes de enviá-la
//        var form = document.querySelector('form');
//        form.addEventListener('submit', function(event) {
//            event.preventDefault();
//
//            var message = {
//                coderele: form.querySelector('input[name="coderele"]').value,
//                comando: {
//                    data: form.querySelector('input[name="comando"]').value
//                }
//            };
//
//            var xhr = new XMLHttpRequest();
//            xhr.open('POST', '/sendMessage');
//            xhr.setRequestHeader('Content-Type', 'application/json');
//            xhr.send(JSON.stringify(message));
//        });
//
//
//    // Faz uma requisição GET para o método `@GetMapping("/reciveMessage")`
//    $.ajax({
//        url: '/reciveMessage',
//        type: 'GET',
//        success: function(response) {
//            // Se a requisição for bem-sucedida, a resposta será "Success"
//            if (response === 'Success') {
//                console.log('Mensagem recebida com sucesso!');
//            } else {
//                console.log('Falha ao receber mensagem!');
//            }
//        },
//        error: function(error) {
//            // Se a requisição falhar, o erro será exibido no console
//            console.log(error);
//        }
//    });