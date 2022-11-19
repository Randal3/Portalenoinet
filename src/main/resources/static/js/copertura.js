
function VerificaCopertura() {

    var formCopertura={
        "lat": $("#latitudine").val(),
        "lon": document.getElementById("longitudine").value,
        "appId": 100
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/SimService",
        data: JSON.stringify(formCopertura),
       
        success: function (data) {
        console.log("SUCCESS : ", data);

        },
        error: function (e) {
        alert("Error!");
        console.log("ERROR: ", e);
        }
    });
}

$( document ).ready(function() {
    console.log( "ready!" );

    $("#FormCopertura").submit(function () {
        event.preventDefault();
      
        VerificaCopertura();
      });

});