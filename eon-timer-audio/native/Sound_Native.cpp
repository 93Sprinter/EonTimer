/**
 * File generated by Jenny -- https://github.com/LanderlYoung/Jenny
 *
 * For bug report, please refer to github issue tracker https://github.com/LanderlYoung/Jenny/issues.
 */
#include "Sound_Native.h"
#include <iostream>
#include <SFML/Audio.hpp>

namespace Sound::Native {
    void allocate$eon_timer_audio(JNIEnv *env, jclass, jobject buffer) {
        auto *address = (jlong *) env->GetDirectBufferAddress(buffer);

        address[0] = (jlong) new sf::Sound();
        address[1] = (jlong) ((void (*)(jlong)) [](jlong rawAddress) {
            std::cout << "deleting sound " << rawAddress << std::endl;
            delete ((sf::Sound *) rawAddress);
        });
    }

    void load$eon_timer_audio(JNIEnv *env, jclass, jlong rawAddress, jobject buffer) {
        auto *sound = (sf::Sound *) rawAddress;
        auto *soundBuffer = new sf::SoundBuffer();
        soundBuffer->loadFromMemory(env->GetDirectBufferAddress(buffer), env->GetDirectBufferCapacity(buffer));
        sound->setBuffer(*soundBuffer);
    }

    void play$eon_timer_audio(JNIEnv *, jclass, jlong rawAddress) {
        ((sf::Sound *) rawAddress)->play();
    }
}
