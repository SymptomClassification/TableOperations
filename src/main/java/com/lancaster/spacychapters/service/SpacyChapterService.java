package com.lancaster.spacychapters.service;

import java.util.List;
import java.util.Map;

public interface SpacyChapterService {

    List<Map<String, String>> getSpacyChapters();

    List<Map<String, String>> getSpacySubChapters();
}
