
feign 配置这两个
---
    feign:
        hystrix:
            enabled: true
---
---
    hystrix:
        command:
            default:
                execution:
                    isolation:
                        thread:
                            timeoutInMilliseconds: 20000
                circuitBreaker:
                    requestVolumeThreshold: 5
--                    