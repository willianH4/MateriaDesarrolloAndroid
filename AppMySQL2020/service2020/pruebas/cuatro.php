<?php
$json = '{
    "Lenguaje": "PHP",
    "Base": "MYSQL"
}';

$data = json_decode($json);

echo $data->title;
echo "\n";

echo $data->site;
?>