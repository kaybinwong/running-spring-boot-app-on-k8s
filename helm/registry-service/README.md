# Spring Cloud Netflix Eureka



## Eureka

Eureka 是 [Spring Cloud Netflix](http://cloud.spring.io/spring-cloud-netflix/) 项目中的发现服务。



## 简介

利用Helm包管理器执行chart可以在Kubernetes集群上部署一个两节点的Eureka Server。



## 命令行部署

执行如下命令安装chart，release名称为 `myeureka`:

```bash
$ helm install --name myeureka incubator/ack-springcloud-eureka
```

命令执行成功后在Kubernetes集群的default名空间中部署了Eureka Server。部署参数可以在vlaues.yaml中指定，在 [配置](#configuration) 一节中列出所有部署中可能需要到的参数和它们的缺省值。

每个Eureka实例均有一个headless Kubernetes服务用来在集群内部访问。如果 `sevice.enabled=true`，则会另外部署一个普通服务，用来从集群外部访问Eureka。



举例讲，如果`replicaCount=2` 并且 `service.enabled=true`, 系统中会有3个服务，包括2个headless服务，1个普通服务。利用Kubectl命令行访问集群可以看到类似如下显示：

```
NAME                                             TYPE           CLUSTER-IP     EXTERNAL-IP    PORT(S)
myeureka-ack-springcloud-eureka-headless-svc-0   ClusterIP      172.21.7.149   <none>         8761/TCP
myeureka-ack-springcloud-eureka-headless-svc-1   ClusterIP      172.21.13.83   <none>         8761/TCP
myeureka-ack-springcloud-eureka-svc              LoadBalancer   172.21.3.184   aa.bb.cc.dd    8761:32737/TCP
```



在本例中，用户可以通过两个headless服务来访问Eureka，分别是`myeureka-ack-springcloud-eureka-headless-svc-0` 和 `myeureka-ack-springcloud-eureka-headless-svc-1` 。用户也可以通过普通服务从集群外部访问Eureka，即`myeureka-ack-springcloud-eureka-svc` 。


## Eureka 控制台

用户可以通过8761端口来访问Euerka控制台，例如Eureka服务地址为`eureka-svc ` , 则控制台的URL为 http://eureka-svc:8761/.  8761端口可以在`Values.yaml` 配置更改，详情见配置一节。



## 如何连接到Eureka

Eureka客户端需要通过在配置文件中设置Eureka Server地址来访问Eureka。根据SpringCloud规约，配置问及爱你可以是`.yaml` 或者  `.properties` 文件。下面示例为在 `application.yaml` 配置访问Eureka。



通过headless服务访问：

```yaml
eureka:
  client:
    serviceUrl:
      zefaultZone: http://myeureka-ack-springcloud-eureka-headless-svc-0.default.svc.cluster.local/eureka,http://myeureka-ack-springcloud-eureka-headless-svc-1.default.svc.cluster.local/eureka
```



通过普通服务访问：

```yaml
eureka:
  client:
    serviceUrl:
      zefaultZone: http://myeureka-ack-springcloud-eureka-svc.default.svc.cluster.local:8761/eureka
```





## 命令行删除

通过命令行删除 `myeureka` 的操作命令如下:

```bash
$ helm delete --purge myeureka
```

执行这个命令会将Eurkea删除，并且把相应helm release也删除。


## 配置

下面是所有Eureka Helm Chart的部署参数，可以在 `Values.yaml` 中设置。

| 参数                            | 描述                                                 | 缺省值                                 |
| ------------------------------------ | ------------------------------------------------------------ | ------------------------------------------ |
| `replicaCount` | Eureka实例数目。`1`  为单实例部署，`2` 为两节点高可用部署。不建议大于2。 | 2 |
| `image.repository`                     | Eureka镜像仓库地址。                                       |                                            |
| `image.tag`                           | Eureka镜像标签。                                | 最新Release              |
| `image.pullPolicy`                    | 镜像拉取策略。                                       | `IfNotPresent`                             |
| `service.enabled`                  | `true` 表示部署一个普通服务对外暴露Eureka。`false` 表示不部署普通服务，仅通过headless服务从集群内部访问Eureka。 | `true`                                  |
| `service.type`        | 服务类型，可选项为`ClusterIP`, `NodePort`, `LoadBalancer`。 | `LoadBalancer`                          |
| `service.externalPort`  | Eureka对外端口号。                      | `8761`                                  |
| `service.internalPort` | Eureka侦听端口号。注意：这个参数不允许更改。 | `8761`                                  |
| `management.endpointsEnabled` | `true` 表示对外暴露springcloud管理端点。`false` 表示不暴露。 | `true` |

