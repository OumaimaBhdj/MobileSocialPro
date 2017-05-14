  <?php
require_once('connect.php');



$sql = "SELECT * FROM conge where etat like '%En attente%' ";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('conge');
        $mydata->addChild('id',$row['id']);
        $mydata->addChild('datedebut',$row['datedebut']);
        $mydata->addChild('nbrjours',$row['nbrjours']);
        $mydata->addChild('type_conge',$row['type_conge']);
        $mydata->addChild('raison',$row['raison']);
        $mydata->addChild('etat',$row['etat']);
        
         }
} else {
    echo "0 results";
}
$conn->close();
   echo( json_encode ($json));
?>