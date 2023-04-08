package contracts

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            description "should get 3 most frequent words in descending frequency and alphabetical order"
            request {
                method GET()
                url("v1/word/nth/frequency") {
                    queryParameters {
                        parameter("text", "The123sun shines!!over tHE lake near THE shore")
                        parameter("n", 3)
                    }
                }
            }
            response {
                status 200
                body(
                        [
                                [
                                        "word": "the",
                                        "frequency": 3
                                ],
                                [
                                        "word": "lake",
                                        "frequency": 1
                                ],
                                [
                                        "word": "near",
                                        "frequency": 1
                                ]
                        ]
                )
            }
        },
        Contract.make {
            description "should get an empty list since text is empty"
            request {
                method GET()
                url("v1/word/nth/frequency") {
                    queryParameters {
                        parameter("text", "")
                        parameter("n", 3)
                    }
                }
            }
            response {
                status 200
                body(
                        []
                )
            }
        },
        Contract.make {
            description "should get an empty list since n is 0"
            request {
                method GET()
                url("v1/word/nth/frequency") {
                    queryParameters {
                        parameter("text", "The123sun shines!!over tHE lake near THE shore")
                        parameter("n", 0)
                    }
                }
            }
            response {
                status 200
                body(
                        []
                )
            }
        },
        Contract.make {
            description "should get an empty list since n is negative"
            request {
                method GET()
                url("v1/word/nth/frequency") {
                    queryParameters {
                        parameter("text", "The123sun shines!!over tHE lake near THE shore")
                        parameter("n", -12)
                    }
                }
            }
            response {
                status 200
                body(
                        []
                )
            }
        }
]