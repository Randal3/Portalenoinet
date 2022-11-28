document.getElementById("aggiungiOperatore").onclick = function() {

    document.getElementById("tabella").style.display="none";
    document.getElementById("aggiungiOperatore").style.display="none";
    document.getElementById("formOperatore").style.display="block";
};



$(document).ready(function () {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/getAllOperatori",
       
        success: function (valore) {
        console.log("SUCCESS : ", valore);
            $('#tableOperatori').DataTable({
                data:valore,
                columns: [
                    { data: 'nome' },
                    { data: 'cognome' },
                    { data: 'numero' },
                ],
            });
        },
        error: function (e) {
        alert("Error!");
        console.log("ERROR: ", e);
        }
    });

    if(window.location.pathname.split("/")[1] == "register"){
        document.getElementById("tabella").style.display="none";
        document.getElementById("aggiungiOperatore").style.display="none";
        document.getElementById("formOperatore").style.display="block";
    }
});
