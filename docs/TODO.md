## 日志分级
### DEBUG
> 开发阶段
- 屏蔽后不影响排错

### INFO
> 基础信息、监控信息
- 请求/耗时监控(接口耗时/IP/Session/请求URL/入参/响应)
- 流程信息(业务关键信息,如: 触发优惠信息、触发定时任务)

### WARN
> 警告, 大部分是自动监控打印的。
- 性能瓶颈问题(65%)
- 慢业务
- 慢查询

### ERROR
> 错误
- 性能瓶颈(80%)
- 业务错误
- 错误异常信息

```java
class Example {
    // 1. 业务型错误日志
    public void f(){
        BigDecimal price = p1 + p2;
        if (BigDecimal.ZERO.compareTo(price) > 0) {
            log.error("金额计算失败, 计算金额小于 0。订单号: {}, 计算结果: {}", orderNo, price);
            throw new RuntimeException("系统繁忙");
        }
    }
    
    // 2. 错误异常信息
    public void f2(){
        try {
            Files.move(p1,p2);
        } catch (IOException e) {
            log.error("迁移 Excel 文件迁移失败。将文件 {} 移动至 {}。错误信息: {}", p1, p2, e);
        }
    }  
}

```
