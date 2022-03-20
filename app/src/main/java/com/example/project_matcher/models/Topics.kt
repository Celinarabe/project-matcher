package com.example.project_matcher.models

import com.example.project_matcher.R

class Topics {
    fun loadTopics(): List<Topic> {
        return listOf<Topic>(
            Topic(R.string.topic1, R.string.desc1),
            Topic(R.string.topic2, R.string.desc2),
            Topic(R.string.topic3, R.string.desc3),
            Topic(R.string.topic4, R.string.desc4),
            Topic(R.string.topic5, R.string.desc5),
            Topic(R.string.topic6, R.string.desc6),
            Topic(R.string.topic7, R.string.desc7),
            Topic(R.string.topic8, R.string.desc8),
            Topic(R.string.topic9, R.string.desc9),
            Topic(R.string.topic10, R.string.desc10),
        )
    }
}