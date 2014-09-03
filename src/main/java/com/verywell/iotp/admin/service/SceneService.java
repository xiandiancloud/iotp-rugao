package com.verywell.iotp.admin.service;

import java.util.List;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.scene.SceneInfo;

/**
 * 场景相关服务
 * 
 * @author yao
 * 
 */
public interface SceneService extends BaseCrudService<SceneInfo, Long>
{
	List<SceneInfo> findByRoomId(Long roomId);

	List<SceneInfo> findByRoomIdAndSceneType(Long roomId, Integer sceneType);

	List<SceneInfo> findBySceneType(Integer sceneType);
}
