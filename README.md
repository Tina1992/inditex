# Inditex test project

## Description
Prices endpoint to retrieve the price that is applicable at a specific time for a brand and product defined.

## Usage
Endpoint definition
```
    GET brands/{brand_id}/products/{product}?applied_time={applied_time}
```
Note: `applied_time` is in ISO format `yyyy-MM-dd'T'HH:mm`

## Assumptions - preconditions
* If no price is applicable, an error will be returned.
* If more than one price is applicable with the same max priority, only one will be returned.
* Start and end date from the database are inclusive limits.