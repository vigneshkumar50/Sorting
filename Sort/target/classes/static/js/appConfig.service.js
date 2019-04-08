(function() {
    var app = angular.module("SortApp");
    app.service("AppDataServiceSvc", function (AppNameSvc) {
        this.name = AppNameSvc;
        this.author = "Ganeamoorthy Subramaniyam";
        this.builtDate = new Date().toDateString();
    });
})();
