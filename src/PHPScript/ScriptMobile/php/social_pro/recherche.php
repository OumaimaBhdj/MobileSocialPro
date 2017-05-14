<?php

require_once('connect.php');
$etat=$_GET['etat'];
//

$sql = "select tache.id,tache.datedebut,tache.datefin,tache.description,fos_user.username,tache.user_id FROM
`Tache`,`fos_user` where user_id is not NULL and tache.user_id =fos_user.id and tache.etat=$etat ";
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
		$mydata->addChild('user_id',$row['user_id']);		
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));


?>