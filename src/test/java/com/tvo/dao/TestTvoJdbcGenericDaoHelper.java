package com.tvo.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.tvo.dao.util.TvoEntityFieldNameAndTypePair;
import com.tvo.dao.util.TvoJdbcGenericDaoHelper;
import com.tvo.entity.AssetArticle;
import com.tvo.entity.AssetRoot;
import com.tvo.entity.AssetVideo;

public class TestTvoJdbcGenericDaoHelper
{
	@Test
	public void testTvoJdbcGenericDaoHelper()
	{
		AssetVideo assetVideo = new AssetVideo();
		TvoEntityFieldNameAndTypePair fieldNameAndTypePair = TvoJdbcGenericDaoHelper.getFiledNameAndType(assetVideo, AssetRoot.class);
		assertTrue(fieldNameAndTypePair != null);
		assertNotNull(fieldNameAndTypePair.getKey().equals("assetRoot"));
		assertTrue(fieldNameAndTypePair.getType().getSimpleName().equals("AssetRoot"));
		fieldNameAndTypePair = TvoJdbcGenericDaoHelper.getFiledNameAndType(assetVideo, AssetArticle.class);
		assertNotNull(fieldNameAndTypePair);
		assertTrue(fieldNameAndTypePair.getKey().equals("assetArticles"));
		assertTrue(fieldNameAndTypePair.getType().getSimpleName().equals("AssetArticle"));
		List<Class<?>> childEnitites = TvoJdbcGenericDaoHelper.getAllAssocaitions(AssetVideo.class);
		assertTrue(childEnitites.size() == 2);
		assertTrue(childEnitites.contains(AssetRoot.class));
		assertTrue(childEnitites.contains(AssetArticle.class));
	}
}
