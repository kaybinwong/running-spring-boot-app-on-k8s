# Spring Cloud Config


## Spring Cloud Config
 
[Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/) provides server and client-side support for externalized configuration in a distributed system. With the Config Server you have a central place to manage external properties for applications across all environments. The concepts on both client and server map identically to the Spring Environment and PropertySource abstractions, so they fit very well with Spring applications, but can be used with any application running in any language. As an application moves through the deployment pipeline from dev to test and into production you can manage the configuration between those environments and be certain that applications have everything they need to run when they migrate. The default implementation of the server storage backend uses git so it easily supports labelled versions of configuration environments, as well as being accessible to a wide range of tooling for managing the content. It is easy to add alternative implementations and plug them in with Spring configuration.



## Introduction

This chart bootstraps a two node Config Server deployment on a [Kubernetes](http://kubernetes.io) cluster using the [Helm](https://helm.sh) package manager. 

## Installing the Chart

To install the chart with the release name `myconfigserver`:

```bash
$ helm install --name myzipkin incubator/ack-springcloud-configserver
```

The command deploys Config Server on the Kubernetes cluster in the default configuration. The configuration section lists the parameters that can be configured during installation.

Config Server can be access by visiting headless service internally, or by visiting normal service internally or externally.

## Config Server

User can access Config Server (default port is 8888).  Service port number can be configured values.yml, details in configuration section.


## Uninstalling the Chart

To uninstall/delete the `myconfigserver` deployment completely:

```bash
$ helm delete --purge myconfigserver
```

The command removes all the Kubernetes components associated with the chart and deletes the release.



## Configuration

The following tables lists the configurable parameters of the chart and their default values.

| Parameter                            | Description                                                  | Default                                    |
| ------------------------------------ | ------------------------------------------------------------ | ------------------------------------------ |
| `replicaCount` |  instance number. 1 as single node deployment, 2 as two node high available deployment. | 2 |
| `image.repository`                     | image repository                                                    |                                            |
| `image.tag`                           | image tag.                                           | Most recent release                        |
| `image.pullPolicy`                    | Image pull policy                                            | `IfNotPresent`                             |
| `service.enabled`                  | If the server can be exposed by a service                                | `true`                                  |
| `service.type`        | Service type. | `LoadBalancer`                          |
| `service.externalPort`  | Service port.                             | `8383`                                  |
| `service.internalPort` |  listening port. Please do NOT change this setting. | `8383`                                  |
| `management.endpointsEnabled` | Expose management endpoints | `true` |

