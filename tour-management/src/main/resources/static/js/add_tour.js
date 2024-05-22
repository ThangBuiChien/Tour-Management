document.addEventListener('DOMContentLoaded', function() {
    function fetchDetailRoutes() {
        var routeId = document.getElementById('routeSelect').value;
        var detailRouteSelect = document.getElementById('detailRouteSelect');

        fetch('/tour/getDetailRoutes?routeId=' + routeId)
            .then(response => response.json())
            .then(data => {
                detailRouteSelect.innerHTML = '';
                data.forEach(detailRoute => {
                    var option = document.createElement('option');
                    option.value = detailRoute.id;
                    option.text = detailRoute.stopLocation;
                    detailRouteSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Error fetching detail routes:', error));
    }

    document.getElementById('routeSelect').addEventListener('change', fetchDetailRoutes);
    fetchDetailRoutes();

    window.addStartDateField = function() {
        var container = document.getElementById('startDatesContainer');
        var input = document.createElement('input');
        input.type = 'date';
        input.name = 'startDates';
        input.className = 'form-control stday mt-2';
        container.appendChild(input);
    };

    window.removeLastStartDate = function() {
        var container = document.getElementById('startDatesContainer');
        var inputs = container.getElementsByTagName('input');
        if (inputs.length > 0) {
            container.removeChild(inputs[inputs.length - 1]);
        }
    };
});
