/*
 * This file is part of Toxopid, a Gradle plugin for Mindustry mods/plugins.
 *
 * MIT License
 *
 * Copyright (c) 2022 Xpdustry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.xpdustry.toxopid.task

import java.net.URL

class GitHubArtifact private constructor(val name: String, val url: URL) : java.io.Serializable {
    companion object {
        /**
         * This artifact is an entire GitHub repo as a zip,
         * usually for javascript and (h)json mods.
         */
        @JvmOverloads
        fun zip(user: String, repo: String, branch: String = "master") =
            GitHubArtifact(
                "$repo-$branch.zip",
                URL("https://github.com/$user/$repo/archive/refs/heads/$branch.zip")
            )

        /**
         * This artifact is a file from a release in a GitHub repo,
         * usually for jvm mods/plugins.
         */
        fun release(user: String, repo: String, version: String, name: String) =
            GitHubArtifact(
                name,
                URL("https://github.com/$user/$repo/releases/download/$version/$name")
            )
    }
}
