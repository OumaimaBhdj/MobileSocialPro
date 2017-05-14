<?php
require_once('connect.php');
$id=$_GET['id'];
$sql = "select * FROM `Tache` where `user_id`=$id and datefin>CURRENT_DATE() and
( (etat like '%pas encore realisee%') or (etat like '%en train de realisation%'))";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>'); 
if ($result->num_rows > 0) {
    // output data of each row
        while($row = $result->fetch_assoc()) {
        $mydata = $json->addChild('tache');
        $mydata->addChild('id',$row['id']);
		$mydata->addChild('datedebut',$row['datedebut']);
		$mydata->addChild('datefin',$row['datefin']);
		$mydata->addChild('description',utf8_decode($row['description']));
		$mydata->addChild('etat',$row['etat']);
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>
