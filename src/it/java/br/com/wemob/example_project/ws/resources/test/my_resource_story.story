Scenario: test my resource
 
When I access the myresource
Then the response should be ok

Scenario: test JSON

Given that I have a user registered
When I access the /user
Then I'd to receive a JSON with that user