
function call() {
    var msg   = $('#formx').serialize();
    $.ajax({
        type: 'POST',
        url: '/dell', //обращаемся к обработчику
        data: msg,
        success: function() {
            alert("Lecture has been deleted" );
        },
        error:  function(){ //ошибка выводит соответствующее сообщение
            alert("Not Successful");
        }
    });
}