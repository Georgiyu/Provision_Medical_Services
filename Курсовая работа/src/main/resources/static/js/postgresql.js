const pg = require('pg').Pool;
var conString = "postgres://postgres:root@localhost:5432/postgres";

var client = new pg.Client(conString);
client.connect();

var query = client.query("SELECT name FROM patients");
//fired after last row is emitted

query.on('row', function(row) {
    console.log(row);
});

query.on('end', function() {
    client.end();
});



//queries can be executed either via text/parameter values passed as individual arguments
//or by passing an options object containing text, (optional) parameter values, and (optional) query name
// client.query({
//     name: 'insert beatle',
//     text: "INSERT INTO beatles(name, height, birthday) values($1, $2, $3)",
//     values: ['George', 70, new Date(1946, 02, 14)]
// });
//
// //subsequent queries with the same name will be executed without re-parsing the query plan by postgres
// client.query({
//     name: 'insert beatle',
//     values: ['Paul', 63, new Date(1945, 04, 03)]
// });