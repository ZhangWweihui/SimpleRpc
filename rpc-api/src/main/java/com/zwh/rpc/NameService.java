package com.zwh.rpc;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

public interface NameService {

    /**
     * 所有支持的协议
     * @return
     */
    Collection<String> supportedSchemes();

    /**
     * 链接注册中心
     * @param nameServiceUri 注册中心地址
     */
    void connect(URI nameServiceUri);

    /**
     * 注册服务
     * @param serviceName 服务名称
     * @param serviceUri 服务URI
     * @throws IOException
     */
    void registerService(String serviceName, URI serviceUri) throws IOException;

    /**
     * 查询服务地址
     * @param serviceName 服务名称
     * @return 服务地址
     * @throws IOException
     */
    URI lookupService(String serviceName) throws IOException;
}
