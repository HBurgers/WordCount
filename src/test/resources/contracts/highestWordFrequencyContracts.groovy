package contracts

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            description "should get 4 as highest frequency"
            request {
                method GET()
                url("v1/word/highest/frequency") {
                    queryParameters {
                        parameter("text", "The123sun shines!!over tHE lake near THE shore")
                    }
                }
            }
            response {
                status 200
                body(3)
            }
        },
        Contract.make {
            description "should get 0 as highest frequency"
            request {
                method GET()
                url("v1/word/highest/frequency") {
                    queryParameters {
                        parameter("text", "")
                    }
                }
            }
            response {
                status 200
                body(0)
            }
        }
]