# backend-cram

### Registration

```shell
curl --location --request POST 'http://localhost:8081/challenge:registration' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId": "1",
    "pk": "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE1Pp8aJj3w61AW78eKxd+wiHYERzJti4I2dngMVVMcZ759TLxLETJPlku5MFkNlGJNLu4GT1bt8lEvxi5h3A8nA=="
}'
```

Response

```json
{
    "code": "200",
    "message": "SUCCESSFUL",
    "data": null
}
```

### Request

```shell
curl --location --request POST 'http://localhost:8081/challenge:request' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId": "1"
}'
```

Response

```json
{
    "code": "200",
    "message": "SUCCESSFUL",
    "data": {
        "challenge": "577cb5f5-34d2-43ea-8a41-c67cdbf80a4a"
    }
}
```

### Verify

```shell
curl --location --request POST 'http://localhost:8081/challenge:verify' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId": "1",
    "challenge": "577cb5f5-34d2-43ea-8a41-c67cdbf80a4a",
    "signature": "MEQCIH6X8KrktBuTaV3StA0TYe4OuGoqoP4EM4hleuHVUlCQAiBHso4R8EUTIUIWfVgI4qpW1qn7Zkz9h0CmyJypgBRyBA==",
    "nonce": "f8a01ad7-abbb-43d6-bc86-d811214e7cf9"
}'
```

Response

```json
{
    "code": "200",
    "message": "SUCCESSFUL",
    "data": {
        "token": "ac7b6cbb-b337-449c-8eb0-b1db7772ec35"
    }
}
```