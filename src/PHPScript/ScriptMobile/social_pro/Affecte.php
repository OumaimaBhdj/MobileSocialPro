<?php
// tache afféctées
require_once('connect.php');
$sql = "select * FROM `Tache`,`fos_user` where datefin> CURRENT_DATE and user_id is not NULL and fos_user.id=tache.user_id ORDER BY datedebut ASC  ";
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
			$mydata->addChild('username',$row['username']);
        
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
	 
	 
?>
