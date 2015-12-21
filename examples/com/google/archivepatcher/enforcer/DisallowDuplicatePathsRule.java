// Copyright 2015 Google Inc. All rights reserved.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.archivepatcher.enforcer;

import java.util.HashSet;
import java.util.Set;

import com.google.archivepatcher.parts.CentralDirectoryFile;

/**
 * Disallows any duplicate paths.
 */
public class DisallowDuplicatePathsRule extends ArchiveRule {

    @Override
    protected void checkInternal() {
        Set<String> pathsSeen = new HashSet<String>();
        for (CentralDirectoryFile cdf :
            archive.getCentralDirectory().entries()) {
            if (pathsSeen.contains(cdf.getFileName())) {
                notOk(cdf.getFileName(), "duplicate path");
                pathsSeen.add(cdf.getFileName());
            }
        }
    }
}
