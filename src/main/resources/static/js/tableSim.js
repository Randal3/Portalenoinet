$(document).ready(function () {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/getAllSim",
       
        success: function (valore) {
        console.log("SUCCESS : ", valore);
            $('#table').DataTable({
                data:valore,
                columns: [
                    { data: 'seriale' },
                    { data: 'tipoServizio' },
                    { data: 'areaCode' },
                    { data: 'data' },
                    { data: 'stato', render: function () { 
                        return '<i class="fa-solid fa-mountain-sun"></i>'
                    
                    
                        }   
                    },
                    { data: 'operatore.nome' },
                    { data: 'dettagli' }
                ],
            });
        },
        error: function (e) {
        alert("Error!");
        console.log("ERROR: ", e);
        }
    });
});
