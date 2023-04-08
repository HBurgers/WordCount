package contracts

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            description "should get 3 as the frequency of the word the"
            request {
                method GET()
                url("v1/word/frequency") {
                    queryParameters {
                        parameter("text", "The123sun shines!!over tHE lake near THE shore")
                        parameter("word", "the")
                    }
                }
            }
            response {
                status 200
                body(3)
            }
        },
        Contract.make {
            description "should get 1 as the frequency of the word lake"
            request {
                method GET()
                url("v1/word/frequency") {
                    queryParameters {
                        parameter("text", "The123sun shines!!over tHE lake near THE shore")
                        parameter("word", "lake")
                    }
                }
            }
            response {
                status 200
                body(1)
            }
        },
        Contract.make {
            description "should get 0 as the frequency of the word nonsense"
            request {
                method GET()
                url("v1/word/frequency") {
                    queryParameters {
                        parameter("text", "The123sun shines!!over tHE lake near THE shore")
                        parameter("word", "nonsense")
                    }
                }
            }
            response {
                status 200
                body(0)
            }
        },
        Contract.make {
            description "should get 0 as the frequency with empty text"
            request {
                method GET()
                url("v1/word/frequency") {
                    queryParameters {
                        parameter("text", "")
                        parameter("word", "word")
                    }
                }
            }
            response {
                status 200
                body(0)
            }
        }
]