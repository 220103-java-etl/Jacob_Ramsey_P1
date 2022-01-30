package com.revature.models;

public enum EventType {
    UNIVERSITY_COURSE {
        @Override
        public String toString() {
            return "University Course";
        }
    },
    SEMINAR {
        @Override
        public String toString() {
            return "Seminar";
        }
    },
    CERTIFICATION_PREP_CLASS {
        @Override
        public String toString() {
            return "Certification Prep Class";
        }
    },
    CERTIFICATION{
        @Override
        public String toString() {
            return "Certification";
        }

    },
    TECHNICAL_TRAINING {
        @Override
        public String toString() {
            return "Technical Training";
        }
    },
    OTHER {
        @Override
        public String toString() {
            return "Other";
        }
    }



}
